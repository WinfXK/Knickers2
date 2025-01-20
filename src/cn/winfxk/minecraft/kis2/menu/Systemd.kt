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
* Created Date: 2025/1/10  16:23 */
package cn.winfxk.minecraft.kis2.menu

import cn.winfxk.libk.config.Config
import cn.winfxk.minecraft.kis2.menu.config.Command
import cn.winfxk.minecraft.kis2.menu.config.Filter
import cn.winfxk.minecraft.kis2.menu.config.Message
import cn.winfxk.minecraft.kis2.menu.config.Metadata
import cn.winfxk.minecraft.kis2.menu.config.button.Buttons
import cn.winfxk.minecraft.kis2.menu.config.player.Player
import java.io.File

class Systemd(private val file: File) {
    private val config = Config(file);
    private var title = config.getString("title") ?: "";
    private var content = config.getString("content") ?: "";
    private val filter = Filter();
    private val player = Player();
    private val metadata = Metadata();
    private val command = Command();
    private val message = Message();
    private val buttons = Buttons();
    fun reload() {
        title = config.getString("title") ?: "";
        content = config.getString("content") ?: "";
        filter.reload(config.get("filter"));
        player.reload(config.get("player"));
        metadata.reload(config.get("metadata"));
        command.reload(config.get("command"));
        message.reload(config.get("message"));
        buttons.reload(config.get("buttons"));
    }

    fun getFile() = file;
    fun getConfig() = config;
    fun getTitle() = title;
    fun getContent() = content;
    fun getFilter() = filter;
    fun getPlayer() = player;
    fun getMetadata() = metadata;
    fun getCommand() = command;
    fun getMessage() = message;
    fun getButtons() = buttons;
}
