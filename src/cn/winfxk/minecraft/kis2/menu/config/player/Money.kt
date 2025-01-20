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
* Created Date: 2025/1/17  17:02 */
package cn.winfxk.minecraft.kis2.menu.config.player

import cn.nukkit.Player
import cn.winfxk.lib.mc.Winfxklib
import cn.winfxk.libk.tool.utils.objToBoolean
import cn.winfxk.libk.tool.utils.objToDouble
import cn.winfxk.libk.tool.utils.objToString

class Money {
    private val floor = MoneyItem();
    private val ceiling = MoneyItem();
    private val deduct = MoneyItem();
    /**
     * 获取最低货币要求的配置
     */
    fun getFloor() = floor;
    /**
     * 获取最高货币要求的配置
     */
    fun getCeiling() = ceiling;
    /**
     * 获取使用单价的配置
     */
    fun getDeduct() = deduct;
    /**
     * 判断一个玩家是否能用<仅仅检测，不扣除>
     */
    fun hasMoney(player: Player): Boolean {
        if (floor.isEnabled())
            if (floor.getMoney(player) < floor.getAmount()) return false;
        if (ceiling.isEnabled())
            if (ceiling.getMoney(player) > ceiling.getAmount()) return false;
        if (deduct.isEnabled())
            if (deduct.getMoney(player) < deduct.getAmount()) return false;
        return true;
    }

    fun reload(any: Any?) {
        val map = if (any == null || any !is Map<*, *>) HashMap() else any;
        floor.reload(map["floor"])
        ceiling.reload(map["ceiling"])
        deduct.reload(map["deduct"])
    }

    class MoneyItem {
        private var enabled = false;
        private var amount = 0.0;
        private var economy = default;
        private var economyKey = default.getKey();

        companion object {
            private val default = Winfxklib.getApi().getBaseEconomy();
        }
        /**
         * 是否启用检测
         */
        fun isEnabled() = enabled;
        /**
         * 要求
         */
        fun getAmount() = amount;
        /**
         * 经济支持
         */
        fun getEconomy() = economy;
        /**
         * 获取玩家对应的货币
         */
        fun getMoney(player: Player): Double = economy.getMoney(player);
        fun reload(any: Any?) {
            enabled = false;
            amount = 0.0;
            economy = default;
            economyKey = default.getKey();
            if (any == null || any !is Map<*, *>) return;
            enabled = any["enabled"].objToBoolean();
            amount = any["amount"].objToDouble(0.0);
            economyKey = any["economy"].objToString() ?: default.getKey();
            if (Winfxklib.economyMagt.hasEconomy(economyKey))
                economy = Winfxklib.economyMagt.getEconomy(economyKey) ?: default
        }
    }
}