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
* Created Date: 2025/1/17  11:13 */
package cn.winfxk.minecraft.kis2.menu.config

import cn.winfxk.libk.tool.utils.objToBoolean
import cn.winfxk.libk.tool.utils.objToString
import cn.winfxk.minecraft.kis2.menu.Systemd

class Filter {
    val whitelist = FilterItem();
    val blacklist = FilterItem();

    /**
     * 重载配置
     */
    fun reload(any: Any?) {
        val map = if (any == null || any !is Map<*, *>) HashMap() else any;
        whitelist.reload(map.get("whitelist"));
        blacklist.reload(map.get("blacklist"));
    }
    /**
     * 检查一个元素是否能通过检查，如果能表示可用
     */
    fun usable(el: String): Boolean {
        if (blacklist.isEnabled()) if (blacklist.contains(el)) return false;
        if (whitelist.isEnabled()) if (! whitelist.contains(el)) return false;
        return true;
    }

    class FilterItem {
        private var enabled = false;
        private val list = ArrayList<String>();
        /**
         * 重载配置
         */
        fun reload(map: Any?) {
            list.clear();
            enabled = false;
            if (map == null || map !is Map<*, *>) return;
            enabled = map["enabled"].objToBoolean(false);
            map["list"]?.also {
                if (it !is List<*>) return@also;
                var str: String?;
                for (l in it) {
                    if (l == null) continue;
                    str = l.objToString();
                    if (str.isNullOrBlank()) continue;
                    if (! list.contains(str)) list.add(str)
                }
            }
        }

        fun isEnabled() = enabled;
        fun getList() = list;
        /**
         * 判断是否在清单内
         */
        fun contains(item: String, ignoreCase: Boolean = false): Boolean {
            if (list.contains(item)) return true;
            if (ignoreCase) for (str in list)
                if (str.equals(item, ignoreCase = true))
                    return true;
            return false;
        }
    }
}