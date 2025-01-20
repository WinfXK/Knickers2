/* 
* Copyright Notice
* © [2024] Winfxk. All rights reserved.
* The software, its source code, and all related documentation are the intellectual property of Winfxk. Any reproduction or distribution of this software or any part thereof must be clearly attributed to Winfxk and the original author. Unauthorized copying, reproduction, or distribution without proper attribution is strictly prohibited.
* For inquiries, support, or to request permission for use, please contact us at:
* Email: admin@winfxk.cn
* QQ: 2508543202
* Visit our homepage for more information: http://Winfxk.cn
* 
* --------- Create message ---------
* Created by IntelliJ ID
* Author： Winfxk
* Created PCUser: kc4064 
* Web: http://winfxk.com
* Created Date: 2025/1/10  11:01 */
package cn.winfxk.minecraft.kis2

import cn.nukkit.plugin.PluginBase
import cn.winfxk.lib.mc.msg.Message
import cn.winfxk.lib.mc.tool.macth
import cn.winfxk.libk.config.Config
import cn.winfxk.libk.tool.Tool
import cn.winfxk.libk.tool.utils.getStreamByJar
import cn.winfxk.libk.tool.utils.readString
import cn.winfxk.libk.tool.utils.toJson
import cn.winfxk.minecraft.kis2.config.Systemd
import cn.winfxk.minecraft.kis2.menu.type.MenuMagt
import java.io.File

class Knickers : PluginBase() {
    private lateinit var configDir: File;
    private lateinit var menusDir: File;
    private var start: Long = System.currentTimeMillis();
    private lateinit var message: Message;
    private lateinit var config: Systemd
    private lateinit var menuMagt: MenuMagt;

    init {
        main = this;
    }

    companion object {
        private lateinit var main: Knickers;
        fun getMain() = main;
    }

    override fun onLoad() {
        logger.info("Winfxklib loading...")
        super.onLoad()
    }

    override fun onDisable() {
        super.onDisable()
        val time = System.currentTimeMillis() - start;
        logger.info(message.getMessage("插件关闭").p(
            "ms" to "${time}ms",
            "s" to "${time / 60}s",
            "elapsed" to Tool.getTimeBy(time)
        ))
    }

    override fun onEnable() {
        start = System.currentTimeMillis();
        configDir = File(dataFolder, "Configs");
        if (! configDir.exists() || configDir.isDirectory) configDir.mkdirs()
        macth()
        logger.info(message.getMessage("插件初始化").p())
        logger.info(message.getMessage("语言加载").p("language" to message.getMessage("language").p()))
        menusDir = File(dataFolder, "Menus");
        config = Systemd();
        config.init();
        menuMagt = MenuMagt();
        val array = config.getMenu().inspect();
        logger.info(message.getMessage("菜单", "菜单数").p(
            "size" to array[1],
            "allSize" to array[0],
            "sbSize" to array[2],
            "path" to config.getMenu().getMenuPath(),
            "default" to menusDir.absolutePath
        ))
        super.onEnable()
        val time = System.currentTimeMillis() - start;
        logger.info(message.getMessage("插件启动").p(
            "ms" to "${time}ms",
            "s" to "${time / 1000}s",
            "elapsed" to Tool.getTimeBy(time)
        ))
    }

    private fun macth() {
        val config = Config(File(configDir, "message.json"));
        config.macth(this.getStreamByJar("message.json")?.readString().toJson(), log = logger)
        message = Message(config.file, log = logger);
        message.addGlobal(
            "pluginName" to name,
            "version" to this.description.version,
        )
    }

    fun getMenuMagt() = menuMagt;
    fun getConfigDir() = configDir;
    fun getMenusDir() = menusDir;
    fun getMessage() = message;
}