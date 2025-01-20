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
* Created Date: 2025/1/17  16:47 */
package cn.winfxk.minecraft.kis2.menu.config.player

import cn.winfxk.libk.tool.utils.objToBoolean

class Permission {
    private var enabled = false;
    private var op = true
    private var create = true
    private var survival = true
    private var spectator = true;
    private var player = true;
    fun reload(any: Any?) {
        if (any == null || any !is Map<*, *>) return;
        enabled = any["enabled"].objToBoolean(false);
        op = any["op"].objToBoolean(true);
        create = any["create"].objToBoolean(true);
        survival = any["survival"].objToBoolean(true);
        spectator = any["spectator"].objToBoolean(true);
        player = any["player"].objToBoolean(true);
    }
    /**
     * 是否启用权限限制
     */
    fun isEnabled() = enabled;
    /**
     * 是否允许OP使用
     */
    fun isOp() = op;
    /**
     * 是否允许普通玩家使用
     */
    fun isPlayer() = player;
    /**
     * 是否允许创超模式使用
     */
    fun isCreate() = create;
    /**
     * 是否允许生存模式使用
     */
    fun isSurvival() = survival;
    /**
     * 是否允许观察者模式使用
     */
    fun isSpectator() = spectator;
}