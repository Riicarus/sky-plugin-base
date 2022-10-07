package com.skyline.plugin;

import java.util.Objects;

/**
 * [FEATURE INFO]<br/>
 * plugin info
 *
 * @author Skyline
 * @create 2022-10-6 20:58
 * @since 1.0.0
 */
public class PluginInfo {

    private String version;

    private String name;

    private String interfaceName;

    private String author;

    public PluginInfo() {
    }

    public PluginInfo(String version, String name, String interfaceName, String author) {
        this.version = version;
        this.name = name;
        this.interfaceName = interfaceName;
        this.author = author;
    }

    @Override
    public String toString() {
        return "PluginInfo{" +
                "version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PluginInfo that = (PluginInfo) o;
        return Objects.equals(version, that.version) && Objects.equals(name, that.name) && Objects.equals(interfaceName, that.interfaceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, name, interfaceName);
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
