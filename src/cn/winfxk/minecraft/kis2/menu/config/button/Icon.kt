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
* Created Date: 2025/1/18  09:32 */
package cn.winfxk.minecraft.kis2.menu.config.button

import cn.winfxk.lib.mc.tool.Itemlist
import cn.winfxk.libk.tool.utils.objToBoolean
import cn.winfxk.libk.tool.utils.objToString

class Icon : Parent {
    private var enabled = false;
    private var type = "local";
    private var path: String? = null;
    /**
     * 是否启用图标
     */
    fun isEnabled() = enabled;
    /**
     * 图标类型
     */
    fun getType() = type;
    /**
     * 图标地址
     */
    fun getPath() = path;
    fun reload(any: Any?) {
        enabled = any?.objToBoolean(enabled) ?: false;
        val t = any?.objToString(type) ?: "local";
        type = when (t.lowercase()) {
            "local", "mc", "minecraft"     -> "local";
            "remote", "url", "web", "http" -> "remote";
            else                           -> "local";
        }
        val str = any?.objToString(path);
        if (str == null) {
            path = null
            return
        }
        val item = Itemlist.getByAny(str, ignoreCase = true, ignoreDamage = true, default = null);
        if (item == null) {
            path = str;
            return;
        }
        path = item.getPath();
    }
}