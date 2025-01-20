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
* Created Date: 2025/1/17  14:49 */
package cn.winfxk.minecraft.kis2.menu.config.player


class Player {
    private val permission = Permission();
    private val money = Money();
    fun reload(any: Any?) {
        val map = if (any == null || any !is Map<*, *>) HashMap() else any;
        permission.reload(map["permission"])
        money.reload(map["money"])
    }
    /**
     * 返回权限配置
     */
    fun getPermission() = permission;
    /**
     * 返回金钱配置
     */
    fun getMoney() = money;
}