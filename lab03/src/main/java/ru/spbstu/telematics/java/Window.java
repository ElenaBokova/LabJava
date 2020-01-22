package ru.spbstu.telematics.java;

import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Window
{
    private boolean full_open;
    private boolean full_close;
    private int opened_percent;
    private int time_passed;
    private int hold_button_time;
    private int delta_time;
    private ReentrantLock locker;
    private Condition condition;
    private Open open;
    private Close close;
    private Thread t_open;
    private Thread t_close;
    
    public Vector<Integer> actions;
    
    
    Window(Vector<Integer> actions, int delta_time)
    {
        full_close = true;
        full_open = false;
        opened_percent = 0;
        time_passed = 0;
        this.delta_time = delta_time;
        hold_button_time = 0;
        this.actions = actions;
        open = new Open(this);
        close = new Close(this);
        locker = new ReentrantLock();
        condition = locker.newCondition();
        t_close = new Thread(close);
        t_open = new Thread(open);
    }
    
    public void Start()
    {
        t_close.start();
        t_open.start();
    }
    
    
    void AddOpened_percent()
    {
        if (!full_open)
        {
            time_passed += delta_time;
            opened_percent += 1;
            CheckWindowStatus();
            System.out.println(opened_percent);
        } else
        {
            System.out.println("Error: the window was already open");
        }
    }
    
    void SubOpened_percent()
    {
        if (!full_close)
        {
            time_passed += delta_time;
            opened_percent -= 1;
            CheckWindowStatus();
            System.out.println(opened_percent);
        } else
        {
            System.out.println("Error: the window was already close");
        }
    }
    
    void CheckWindowStatus()
    {
        if (opened_percent == 0)
        {
            full_close = true;
            full_open = false;
        } else if (opened_percent == 100)
        {
            full_close = false;
            full_open = true;
        } else
        {
            full_close = false;
            full_open = false;
        }
    }
    
    synchronized void start_close()
    {
        locker.lock();
        try
        {
            while (actions.size() != 0 && actions.get(0) < 0)
                condition.await();
            if (actions.size() != 0)
            {
                hold_button_time = actions.get(0);
                actions.remove(0);
                if (hold_button_time < 5 * delta_time)
                    hold_button_time = 100 * delta_time;
                time_passed = 0;
                
                System.out.println("Start close");
                while (time_passed < hold_button_time)
                {
                    if (full_close)
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(delta_time);
                        SubOpened_percent();
                    } catch (InterruptedException e)
                    {
                        System.out.println("Thread has been interrupted");
                    }
                }
                System.out.println("End close");
            }
            condition.signalAll();
        } catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            locker.unlock();
        }
    }
    
    void start_open()
    {
        locker.lock();
        try
        {
            while (actions.size() != 0 && actions.get(0) > 0)
                condition.await();
            if (actions.size() != 0)
            {
                hold_button_time = actions.get(0) * -1;
                actions.remove(0);
                if (hold_button_time < 5 * delta_time)
                    hold_button_time = 100 * delta_time;
                time_passed = 0;
                
                System.out.println("Start open");
                while (time_passed < hold_button_time)
                {
                    if (full_open)
                    {
                        break;
                    }
                    try
                    {
                        Thread.sleep(delta_time);
                        AddOpened_percent();
                    } catch (InterruptedException e)
                    {
                        System.out.println("Thread has been interrupted");
                    }
                }
                System.out.println("End open");
            }
            condition.signalAll();
        } catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        } finally
        {
            locker.unlock();
        }
    }
}
