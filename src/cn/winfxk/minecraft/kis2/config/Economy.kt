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
* Created Date: 2025/1/10  11:22 */
package cn.winfxk.minecraft.kis2.config

import cn.winfxk.lib.mc.Winfxklib
import cn.winfxk.lib.mc.economy.EasyEconomy
import cn.winfxk.lib.mc.economy.MyEconomy
import cn.winfxk.libk.tool.utils.objToBoolean
import cn.winfxk.libk.tool.utils.objToString

class Economy {
    @Volatile private var custom: Boolean = true;
    @Volatile private var symbol: String = "$";
    @Volatile private var name: String = "金币";
    @Volatile private var key: String = EasyEconomy.key;
    @Volatile private var economy: MyEconomy = Winfxklib.getApi().getBaseEconomy();
    /**
     * 初始化配置
     */
    fun init(map: Map<*, *>) {
        custom = map["自定义经济"].objToBoolean(false);
        symbol = map["经济符号"].objToString() ?: "$";
        name = map["经济名称"].objToString() ?: "金币";
        if (custom) {
            val key = map["经济Key"].objToString() ?: EasyEconomy.key;
            economy = Winfxklib.economyMagt.getEconomy(key) ?: Winfxklib.getApi().getBaseEconomy()
        } else economy = Winfxklib.getApi().getBaseEconomy();
        key = economy.getKey();
    }
    /**
     * 是否使用自定义经济
     */
    fun isCustom() = custom;
    /**
     * 货币单位<$>
     */
    fun getSymbol() = symbol;
    /**
     * 货币名称<金币>
     */
    fun getName() = name;
    /**
     * 经济Key<EasyEconomy>
     */
    fun getKey() = key;
    /**
     * 经济API<EasyEconomy>
     */
    fun getEconomy() = economy;
}