package com.example.plugin;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.console.extension.PluginComponentStorage;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendAddEvent;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.NewFriendRequestEvent;
import net.mamoe.mirai.message.data.*;
import org.jetbrains.annotations.NotNull;


public class Template extends JavaPlugin {
    public static Template INSTANCE = new Template();

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

    }
}