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
* Created Date: 2025/1/18  12:57 */
package cn.winfxk.minecraft.kis2.menu.type

class MenuMagt {
    private val map = HashMap<String, BaseMenu>();
    /**
     * 判断一个菜单按钮是否存在
     */
    fun has(key: String): Boolean = map.containsKey(key) && map[key] != null;
    /**
     * 添加一个菜单按钮支持
     */
    fun add(menu: BaseMenu): Boolean {
        if (has(menu.getKey())) return false;
        map[menu.getKey()] = menu;
        return true;
    }
    /**
     * 删除一个菜单按钮支持
     */
    fun remove(key: String): Boolean {
        if (! has(key)) return false;
        map.remove(key);
        return true;
    }
}