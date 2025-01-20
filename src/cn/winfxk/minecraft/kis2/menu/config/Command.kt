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
* Created Date: 2025/1/18  07:57 */
package cn.winfxk.minecraft.kis2.menu.config

import cn.winfxk.libk.tool.utils.objToBoolean
import cn.winfxk.libk.tool.utils.objToLong
import cn.winfxk.libk.tool.utils.objToString

class Command {
    private var enabled = false;
    private val list = ArrayList<CommandItem>();
    /**
     * 获取是否启用
     */
    fun isEnabled() = enabled;
    /**
     * 获取所有的命令项
     */
    fun getCommandItems() = list;
    fun reload(any: Any?) {
        list.clear();
        enabled = false;
        if (any == null || any !is Map<*, *>) return;
        enabled = any["enabled"].objToBoolean(false);
        val commands = any["commands"];
        if (commands == null || commands !is List<*>) return;
        for (c in commands) {
            if (c == null) continue
            list.add(CommandItem().apply { reload(c) })
        }
    }

    class CommandItem {
        private var command: String? = null;
        private var delay: Long = 10;
        private var permission: String? = null;
        /**
         * 返回要执行的命令
         */
        fun getCommand() = command;
        /**
         * 返回执行的延时
         */
        fun getDelay() = delay;
        /**
         * 返回执行的权限
         */
        fun getPermission() = permission;
        fun reload(any: Any?) {
            command = null;
            delay = 10;
            permission = null;
            if (any == null || any !is Map<*, *>) return;
            command = any["command"].objToString();
            delay = any["delay"].objToLong(10);
            permission = any["permission"].objToString();
        }
    }
}