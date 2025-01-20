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
* Created Date: 2025/1/18  09:03 */
package cn.winfxk.minecraft.kis2.menu.config.button

class Buttons : Map<String, Button> {
    private val list = LinkedHashMap<String, Button>();
    fun reload(any: Any?) {
        list.clear()
        if (any == null || any !is Map<*, *>) return;
        for ((k, v) in any)
            Button(k.toString(), v, this).also {
                list[it.getKey()] = it;
            }
    }

    fun getButtons() = list;
    fun getButton(key: String) = list[key];
    override val entries: Set<Map.Entry<String, Button>> get() = list.entries;
    override val keys: Set<String> get() = list.keys;
    override val size: Int get() = list.size;
    override val values: Collection<Button> get() = list.values
    override fun isEmpty() = list.isEmpty()
    override fun get(key: String) = list[key];
    override fun containsValue(value: Button) = list.containsValue(value)
    override fun containsKey(key: String) = list.containsKey(key)
}