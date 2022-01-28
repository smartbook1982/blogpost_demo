package com.blog.post.demo.processor;

import com.blog.post.demo.domain.Blog;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class BlogManager implements Runnable {
    BlockingQueue<Blog> queue = new LinkedBlockingQueue<>();
    ConcurrentHashMap<Long, Blog> blogConcurrentHashMap = new ConcurrentHashMap<>();
    Thread serviceThread;
    final private AtomicBoolean isServiceStop = new AtomicBoolean(true);

    public void start () {
        serviceThread = new Thread (this, "blog Manager");
        serviceThread.start();
        isServiceStop.set(false);

    }

    @Override
    public void run() {
        while (!isServiceStop.get())
        {
            try {
                Thread.sleep(100);
                processQueue ();
            } catch (InterruptedException e) {
                serviceThread.interrupt();
                isServiceStop.set(true);
            }
            catch (Throwable e)
            {
                // log blog post error;
            }
        }
    }

    protected void processQueue () throws Throwable
    {
        int size = queue.size();
        if (size == 0) return;

        for (int i = 0; i < size; i ++)
        {
            Blog blog = queue.take();
            // save to cache;
            blogConcurrentHashMap.put(blog.getId(), blog);
            // save to local db ?
        }
    }

    public void addBlog (Blog blog) {
        this.queue.add(blog);
    }

    public Blog getBlogPost (long id)
    {
        return blogConcurrentHashMap.get(id);
    }
}
