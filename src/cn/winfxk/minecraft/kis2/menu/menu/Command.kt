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
* Created Date: 2025/1/20  08:59 */
package cn.winfxk.minecraft.kis2.menu.menu

import cn.winfxk.minecraft.kis2.Knickers
import cn.winfxk.minecraft.kis2.menu.type.BaseMenu

class Command : BaseMenu(key, name) {
    companion object {
        private const val key = "command";
        private val name = main.getMessage().getMessage("菜单", "类型", "Command").p()
    }
}