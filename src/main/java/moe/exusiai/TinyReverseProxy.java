package moe.exusiai;

import moe.exusiai.netty.NettyServer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class TinyReverseProxy extends JavaPlugin {
    public static File folder;
    public static FileConfiguration config;

    public static Integer localport;
    public static Integer publicport;
    public static String publicdoamin;
    public static String certname;
    public static String certpassword;

    @Override
    public void onEnable() {
        folder = this.getDataFolder();
        folder.mkdirs();
        this.saveDefaultConfig();
        config = this.getConfig();

        localport = config.getInt("localport");
        publicport = config.getInt("publicport");
        publicdoamin = config.getString("publicdomain");
        certname = config.getString("cert.name");
        certpassword = config.getString("cert.password");

        this.getLogger().info("TinyReverseProxy by");
        this.getLogger().info("███████╗██╗  ██╗██╗   ██╗███████╗██╗ █████╗ ██╗███╗   ███╗ ██████╗ ███████╗");
        this.getLogger().info("██╔════╝╚██╗██╔╝██║   ██║██╔════╝██║██╔══██╗██║████╗ ████║██╔═══██╗██╔════╝");
        this.getLogger().info("█████╗   ╚███╔╝ ██║   ██║███████╗██║███████║██║██╔████╔██║██║   ██║█████╗  ");
        this.getLogger().info("██╔══╝   ██╔██╗ ██║   ██║╚════██║██║██╔══██║██║██║╚██╔╝██║██║   ██║██╔══╝  ");
        this.getLogger().info("███████╗██╔╝ ██╗╚██████╔╝███████║██║██║  ██║██║██║ ╚═╝ ██║╚██████╔╝███████╗");
        this.getLogger().info("╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝╚═╝  ╚═╝╚═╝╚═╝     ╚═╝ ╚═════╝ ╚══════╝");
        Thread thread = new NettyThread();
        thread.start();
    }
}

class NettyThread extends Thread {
    @Override
    public void run() {
        NettyServer nettyServer = new NettyServer();
        nettyServer.Start();
    }
}
