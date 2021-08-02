package com.example.plugin;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;


public class Template extends JavaPlugin {
    public static Template INSTANCE = new Template();
    private static boolean status = false;

    private Template() {
        super(new JvmPluginDescriptionBuilder(
                        "com.example.plugin.Test", // 需要遵循语法规定，不知道写什么的话就写主类名吧
                        "1.0.0" // 同样需要遵循语法规定
                )
                        .author("me")
                        .name("template")
                        .info("新版本测试")
                        .build()
        );
    }

    @Override
    public void onLoad(@NotNull PluginComponentStorage $this$onLoad) {
    }

    @Override
    public void onEnable() {
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, (FriendMessageEvent e) -> {
            if (e.getSender().getId() == 815153150L) {
                if (status) {
                    e.getSender().sendMessage("already start");
                } else {
                    status = true;
                    sendThread(e);
                    e.getSender().sendMessage("start...");
                }
            }
        });
//        AtomicInteger tag = new AtomicInteger();
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Friend friend = Bot.getInstance(3634917467L).getFriend(815153150L);
//        try {
//            while (true) {
//                GlobalEventChannel.INSTANCE.subscribeOnce(FriendMessageEvent.class,(FriendMessageEvent e)->{
//                    if (e.getSender().getId()==815153150L)
//                        tag.set(1);
//                });
//                if (Integer.parseInt(loadRedis(0)) > 0) {
////                    friend.sendMessage(loadRedis(1));
//                }else {
//                    loadRedis(1);
//                }
//                if (tag.equals(1)) break;
//                Thread.sleep(100);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Group group = Bot.getInstance(3634917467L).getGroup(1063117299);

//        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, (FriendMessageEvent event) -> {
//            event.getSender().sendMessage(event.getMessage());
//            try {
//                event.getSender().sendMessage(getMessages());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//            event.getMessage().stream().filter(message -> message instanceof Image).forEach(mes->
//                    Bot.getInstance(3634917467L).getGroup(1063117299).sendMessage(mes));
//            event.getSender().sendMessage(event.getMessage());
//            StringBuilder sb = new StringBuilder();
//            event.getMessage().stream().filter(message -> message instanceof PlainText).forEach(mes->
//                    sb.append(mes));
//        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, (GroupMessageEvent event) -> {
//            event.getSender().nudge().sendTo(event.getSender());
//            if (event.getSender().getId() == 815153150) {
//                event.getSender().sendMessage("kp1nz!");
//            } else {
//                event.getSender().sendMessage("喵🐱");
//            }
//        });
    }

    private void sendThread(FriendMessageEvent e) {
        Group group_jf = Bot.getInstance(3634917467L).getGroup(1067240772);
        Group group_yj = Bot.getInstance(3634917467L).getGroup(528077046);
        Group group_xwl = Bot.getInstance(3634917467L).getGroup(247856499);
        //简放
        Thread jf = new Thread(() -> {
            while (true) {
                try {
                    if (status && !"null".equals(loadRedis(0, "jf")) && Integer.parseInt(loadRedis(0, "jf")) > 0) {
                        group_jf.sendMessage(loadRedis(1, "jf"));
                    }
                    Thread.sleep(500);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        //越甲
        Thread yj = new Thread(() -> {
            while (true) {
                try {
                    if (status && !"null".equals(loadRedis(0, "yj")) && Integer.parseInt(loadRedis(0, "yj")) > 0) {
                        group_yj.sendMessage(loadRedis(1, "yj"));
                    }
                    Thread.sleep(500);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        //徐闻亮
        Thread xwl = new Thread(() -> {
            while (true) {
                try {
                    if (status && !"null".equals(loadRedis(0, "xwl")) && Integer.parseInt(loadRedis(0, "xwl")) > 0) {
                        group_xwl.sendMessage(loadRedis(1, "xwl"));
                    }
                    Thread.sleep(500);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        jf.start();
        yj.start();
        xwl.start();
    }

    /**
     * @param way 0 = getLength
     */
    public String loadRedis(int way, String key) throws MalformedURLException {
        String len = null, mes = null;
        try {
            //动态载入指定类
            File file = new File("/Users/kp1nz/Downloads/jedis-2.9.0.jar");//类路径(包文件上一层)
            URL url = file.toURI().toURL();
            ClassLoader loader = new URLClassLoader(new URL[]{url});//创建类载入器
            Class<?> cls = loader.loadClass("redis.clients.jedis.Jedis");//载入指定类。注意一定要带上类的包名
            Constructor<?> constructor = cls.getConstructor(String.class, int.class);
            Object o = constructor.newInstance("127.0.0.1", 9736);
            Method auth = cls.getMethod("auth", String.class);//方法名和相应的參数类型
            Object jedis = auth.invoke(o, "90ed6019-cd3b-4cc7-998f-f65fe1c611ec");//调用得到的上边的方法method
            if (way == 0) {
                Method llen = cls.getMethod("llen", String.class);
                len = String.valueOf(llen.invoke(o, key));
            } else {
                Method pop = cls.getMethod("lpop", String.class);
                mes = String.valueOf(pop.invoke(o, key));
            }
        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (way == 0) {
            return len;
        } else {
            return mes;
        }
    }
}