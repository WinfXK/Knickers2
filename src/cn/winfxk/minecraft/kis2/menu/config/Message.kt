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
* Created Date: 2025/1/18  08:56 */
package cn.winfxk.minecraft.kis2.menu.config

import cn.winfxk.libk.tool.utils.objToBoolean
import cn.winfxk.libk.tool.utils.objToLong
import cn.winfxk.libk.tool.utils.objToString
import cn.winfxk.minecraft.kis2.menu.Systemd

class Message {
    private var enabled = false;
    private val list = ArrayList<MessageItem>()
    fun reload(any: Any?) {
        enabled = false;
        list.clear();
        if (any == null || any !is Map<*, *>) return;
        enabled = any["enabled"].objToBoolean(false);
        val messages = any["messages"];
        if (messages == null || messages !is List<*>) return;
        for (a in messages) {
            if (a == null) continue;
            list.add(MessageItem().apply { reload(a) })
        }
    }

    class MessageItem {
        private var delay: Long = 10;
        private var message: String? = null;
        /**
         * 消息延时
         */
        fun getDelay() = delay;
        /**
         * 要发送的消息
         */
        fun getMessage() = message;
        fun reload(any: Any?) {
            delay = 10;
            message = null;
            if (any == null || any !is Map<*, *>) return;
            delay = any["delay"].objToLong(10);
            message = any["message"].objToString();
        }
    }
}