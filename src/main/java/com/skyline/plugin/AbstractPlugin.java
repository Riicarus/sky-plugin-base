package com.skyline.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * [FEATURE INFO]<br/>
 * 插件接口
 *
 * @author Skyline
 * @create 2022-10-6 0:00
 * @since 1.0.0
 */
public abstract class AbstractPlugin {

    protected final static String CONFIG_PATH = "config/plugin.properties";

    protected final PluginInfo pluginInfo;

    public AbstractPlugin() {
        this.pluginInfo = loadPluginInfo();
    }

    /**
     * 插件服务入口, 所有插件服务的服务只能通过调用该接口实现
     *
     * @param args 插件运行需要的参数
     * @return 插件运行返回的值
     */
    public abstract Object run(Object[] args);

    /**
     * 插件加载时执行的方法, 可能会打印一些信息或做一些初始化
     *
     * @param args 插件加载需要的参数
     * @return 是否加载成功
     */
    public abstract boolean install(Object[] args);

    /**
     * 插件卸载时执行的方法, 可能会打印一些信息或关闭相关资源
     *
     * @return 是否卸载成功
     */
    public abstract boolean uninstall();

    /**
     * 初始化时加载插件配置属性
     *
     * @return 插件配置属性
     */
    public PluginInfo loadPluginInfo() {
        Properties properties = new Properties();

        InputStream in;
        InputStreamReader reader;
        try {
            in = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PATH);
            reader = new InputStreamReader(Objects.requireNonNull(in), StandardCharsets.UTF_8);
            properties.load(reader);
        } catch (Exception e) {
            throw new RuntimeException("从路径: " + CONFIG_PATH + " 加载配置文件失败.");
        }

        PluginInfo pluginInfo = new PluginInfo();
        if (properties.containsKey("version") && properties.containsKey("name") && properties.containsKey("interfaceName") && properties.containsKey("author")) {
            pluginInfo.setVersion(properties.getProperty("version"));
            pluginInfo.setName(properties.getProperty("name"));
            pluginInfo.setInterfaceName(properties.getProperty("interfaceName"));
            pluginInfo.setAuthor(properties.getProperty("author"));
        } else {
            throw new RuntimeException("配置格式错误, 加载插件失败.");
        }

        try {
            in.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("关闭配置读入流失败.");
        }

        return pluginInfo;
    }

    /**
     * 获取插件相关信息
     *
     * @return PluginInfo
     */
    public PluginInfo getPluginInfo() {
        return pluginInfo;
    }

    @Override
    public final int hashCode() {
        return getPluginInfo().getId();
    }

    @Override
    public final boolean equals(Object obj) {
        // 比较 PluginInfo 是否相同
        if (AbstractPlugin.class.isAssignableFrom(obj.getClass())) {
            return getPluginInfo().equals(((AbstractPlugin) obj).getPluginInfo());
        }

        return false;
    }
}