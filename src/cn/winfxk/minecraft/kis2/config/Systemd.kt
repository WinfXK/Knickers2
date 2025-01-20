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
* Created Date: 2025/1/10  11:21 */
package cn.winfxk.minecraft.kis2.config

import cn.winfxk.lib.mc.tool.macth
import cn.winfxk.libk.config.Config
import cn.winfxk.libk.tool.utils.getStreamByJar
import cn.winfxk.libk.tool.utils.readString
import cn.winfxk.libk.tool.utils.toJson
import cn.winfxk.minecraft.kis2.Knickers
import java.io.File

class Systemd {
    private val economy = Economy();
    private val menu = Menu();
    private val tool = Shortcutool();
    private val log = Knickers.getMain().logger;
    private val msg = Knickers.getMain().getMessage();
    private val config = Config(File(Knickers.getMain().getConfigDir(), "config.json"));

    init {
        main = this;
    }

    companion object {
        private lateinit var main: Systemd;
        fun getMain() = main;
    }

    fun init() {
        try {
            config.reload();
            config.macth(this.getStreamByJar(config.file.name)?.readString().toJson(), log, true)
            var any = config.get("经济");
            try {
                if (any == null || any !is Map<*, *>) {
                    log.warning(msg.getMessage("配置加载失败").p("key" to "经济", "value" to any, "error" to "一个不兼容的经济设置！请检查您的配置。"))
                    any = HashMap<String, Any?>();
                }
                economy.init(any as Map<*, *>)
            } catch (e: Exception) {
                log.error(msg.getMessage("配置加载失败").p("key" to "经济", "value" to any, "error" to e.message), e)
                economy.init(HashMap<String, Any>())
            }
            any = config.get("菜单");
            try {
                if (any == null || any !is Map<*, *>) {
                    log.warning(msg.getMessage("配置加载失败").p("key" to "菜单", "value" to any, "error" to "一个不兼容的菜单设置！请检查您的配置。"))
                    any = HashMap<String, Any?>();
                }
                menu.init(any as Map<*, *>)
            } catch (e: Exception) {
                log.error(msg.getMessage("配置加载失败").p("key" to "菜单", "value" to any, "error" to e.message), e)
                menu.init(HashMap<String, Any>())
            }
            any = config.get("快捷工具");
            try {
                if (any == null || any !is Map<*, *>) {
                    log.warning(msg.getMessage("配置加载失败").p("key" to "快捷工具", "value" to any, "error" to "一个不兼容的快捷工具设置！请检查您的配置。"))
                    any = HashMap<String, Any?>();
                }
                tool.init(any as Map<*, *>)
            } catch (e: Exception) {
                log.warning(msg.getMessage("配置加载失败").p("key" to "快捷工具", "value" to any, "error" to e.message))
                tool.init(HashMap<String, Any>())
            }
        } catch (e: Exception) {
            log.error(msg.getMessage("插件初始化异常").p("error" to e.message), e)
        }
    }

    fun getEconomy() = economy
    fun getMenu() = menu
    fun getTool() = tool
    fun getConfig() = config;
}