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
* Created Date: 2025/1/18  09:04 */
package cn.winfxk.minecraft.kis2.menu.config.button

import cn.winfxk.libk.tool.utils.objToString
import cn.winfxk.minecraft.kis2.Knickers
import cn.winfxk.minecraft.kis2.menu.config.Command
import cn.winfxk.minecraft.kis2.menu.config.Filter
import cn.winfxk.minecraft.kis2.menu.config.Message
import cn.winfxk.minecraft.kis2.menu.config.Metadata
import cn.winfxk.minecraft.kis2.menu.config.player.Player

class Button(private val key: String, private val any: Any?, private val buttons: Buttons)  {
    private var type: String = "command";
    private var text: String? = null;
    private val filter = Filter();
    private val player = Player();
    private val command = Command();
    private val message = Message();
    private val icon = Icon();
    private val metadata = Metadata();
    private val data = HashMap<String, Any?>();

    companion object {
        private val main = Knickers.getMain();
        private val message = main.getMessage();
    }
    /**
     * 按钮类型
     */
    fun getType() = type;
    /**
     *
     */
    fun getText() = text;
    fun getFilter() = filter;
    fun getPlayer() = player;
    fun getCommand() = command;
    fun getMessage() = message;
    fun getIcon() = icon;
    fun getMetadata() = metadata;
    fun getData() = data;
    fun getKey() = key;

    init {
        reload();
    }

    fun reload() {
        val map = if (any == null || any !is Map<*, *>) HashMap<String, Any?>() else any;
        type = map["type"].objToString("command") ?: type;
        if (! main.getMenuMagt().has(type))
            main.logger.warning(
                Button.message.getMessage("菜单", "菜单数").p(getGlobal()))
        text = map["text"].objToString();
        filter.reload(map["filter"]);
        player.reload(map["player"]);
        command.reload(map["command"]);
        message.reload(map["message"]);
        icon.reload(map["icon"]);
        metadata.reload(map["metadata"]);
        data.clear();
        map["data"]?.also {
            if (it is Map<*, *>) for ((k, v) in it) data[k.toString()] = v;
        }
    }

    fun getGlobal() = listOf(
        "type" to type,
        "text" to text,
    )
}