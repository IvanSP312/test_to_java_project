package ru.testPing;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
    Toolkit toolkit;

    Timer timer;

    public MyTimer(int seconds)
    {
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), (seconds+1) * 1000, seconds * 1000); //вешаем задание таймеру (второй аргумент - через сколько всё начнётся, а третий - интервал, каждые seconds секунд таймер будет запускать метод run())
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("3 секунды прошло!");
            toolkit.beep(); //подаём звуковой сигнал
            //если таймер нужно остановить, то:
            //timer.cancel(); или
            //System.exit(0); завершает поток, в котором идёт выполнение
        }
    }
}
