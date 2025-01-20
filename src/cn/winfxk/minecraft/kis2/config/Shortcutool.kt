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
* Created Date: 2025/1/10  13:18 */
package cn.winfxk.minecraft.kis2.config

import cn.nukkit.item.Item
import cn.nukkit.item.enchantment.Enchantment
import cn.nukkit.nbt.tag.CompoundTag
import cn.winfxk.lib.mc.tool.Itemlist
import cn.winfxk.libk.tool.Tool
import cn.winfxk.libk.tool.utils.objToBoolean
import cn.winfxk.libk.tool.utils.objToInt
import cn.winfxk.libk.tool.utils.objToString
import cn.winfxk.minecraft.kis2.Knickers

class Shortcutool {
    @Volatile private var isDiscard: Boolean = false
    @Volatile private var isLockPosition: Boolean = false
    @Volatile private var backpackPosition: Int = 0
    @Volatile private var item: Item = Item(347, 0, 1, "快捷工具");
    @Volatile private var itemID: String = "347:0";
    @Volatile private var checkTime: Int = 3600 * 1000;
    @Volatile private var isEventReturn: Boolean = true;
    @Volatile private var touchDelay: Int = 500;
    private val filter = Filter();

    init {
        api = this;
    }
    /**
     * 是否允许玩家丢弃快捷工具
     */
    fun isDiscard() = isDiscard;
    /**
     * 是否固定快捷工具在背包中的位置
     */
    fun isLockPosition() = isLockPosition;
    /**
     * 如果需要锁定快捷工具在背包中的位置，那么将会锁定在那个位置
     */
    fun getBackpackPosition() = backpackPosition;
    /**
     * 返回一个全新的快捷工具对象
     */
    fun getItem() = item.clone();
    /**
     * 配置的快捷工具ID
     */
    fun getItemID() = itemID;
    /**
     * 定时检查玩家背包是否有快捷工具，如果没有，就赐予一个，当这个值小于等于0时不启用
     */
    fun getCheckTime() = checkTime;
    /**
     * 是否撤回快捷工具产生的事件
     */
    fun isEventReturn() = isEventReturn;
    /**
     * 返回快捷工具防误触的检测时间<毫秒>
     */
    fun getTouchDelay() = touchDelay;
    /**
     * 返回快捷工具过滤选项
     */
    fun filter() = filter;

    companion object {
        private val main = Knickers.getMain();
        private val msg = main.getMessage();
        private val log = main.logger;
        private lateinit var api: Shortcutool;
        /**
         * 判断物品是否是快捷工具
         */
        fun isItem(item: Item): Boolean {
            if (item.id != api.item.id || item.damage != api.item.damage) return false;
            val nbt = item.namedTag ?: return false;
            if (! nbt.getBoolean("${main.fullName} Tools")) return false;
            nbt.putBoolean("${main.fullName} Tools", true);
            val suaibi = nbt.getString("The most handsome person in the world is");
            val sb = nbt.getString("The ugliest person in the world is");
            if (! suaibi.equals("Winfxk")) return false;
            if (! sb.equals("FFF")) return false;
            return true;
        }
    }
    /**
     * 初始化快捷工具数据
     */
    fun init(map: Map<*, *>) {
        isDiscard = map["允许丢弃"].objToBoolean(false);
        isLockPosition = map["位置锁定"].objToBoolean(false);
        backpackPosition = map["背包位置"].objToInt(0);
        itemID = map["工具物品"].objToString() ?: "347:0";
        checkTime = map["定时检查"].objToInt(3600 * 1000);
        isEventReturn = map["事件撤回"].objToBoolean(true);
        touchDelay = map["误触延时"].objToInt(500);
        var any = map["玩家过滤"];
        if (any == null || any !is Map<*, *>) {
            log.warning(msg.getMessage("配置加载失败").p(
                "key" to "玩家过滤",
                "value" to any,
                "error" to "一个不兼容的快捷工具过滤设置！配置异常！请检查。"
            ))
            any = HashMap<String, Any?>();
        }
        filter.init(any as Map<*, *>)
        val itemlist = Itemlist.getByAny(itemID);
        var item = Item(347, 0, 1, "快捷工具");
        if (itemlist == null) {
            if (Tool.isInteger(itemID)) {
                item = Item(itemID.toInt(), 0, 1, "快捷工具");
            } else
                if (itemID.contains(":")) {
                    val sp = itemID.split(":");
                    val sp1 = sp[0];
                    val sp2 = sp[1];
                    if (! Tool.isInteger(sp1)) {
                        item = Item(347, 0, 1, "快捷工具");
                        log.warning(msg.getMessage("配置加载失败").p(
                            "key" to "工具物品",
                            "value" to map["工具物品"],
                            "error" to "一个不兼容的物品设置！物品ID仅支持纯数字，例如[347:0]"
                        ))
                    } else if (! Tool.isInteger(sp2)) {
                        item = Item(sp1.objToInt(347), 0, 1, "快捷工具");
                        log.warning(msg.getMessage("配置加载失败").p(
                            "key" to "工具物品",
                            "value" to map["工具物品"],
                            "error" to "一个不兼容的物品设置！物品特殊值仅支持纯数字，例如[347:0]"
                        ))
                    } else item = Item(sp1.objToInt(), sp2.objToInt(), 1, "快捷工具");
                } else {
                    item = Item(347, 0, 1, "快捷工具");
                    log.warning(msg.getMessage("配置加载失败").p(
                        "key" to "工具物品",
                        "value" to map["工具物品"],
                        "error" to "一个不兼容的物品设置！请显式的设置物品ID，例如[347:0]"
                    ))
                }
        } else item = itemlist.getItem();
        itemID = "${item.id}:${item.damage}"
        item.setCustomName(msg.getMessage("快捷工具", "名称").p())
        item.setLore(msg.getMessage("快捷工具", "lore").p())
        itemEnchant(item);
    }
    /**
     * 给快捷工具打标记
     */
    private fun itemEnchant(item: Item) {
        var enchant: Enchantment;
        val enchants = Enchantment.getEnchantments();
        for (i in 0 .. Tool.getRand(2, 5)) {
            enchant = enchants[Tool.getRand(0, enchants.size - 1)];
            enchant.setLevel(Tool.getRand(1, 10086));
            item.addEnchantment(enchant);
        }
        val nbt = item.namedTag ?: CompoundTag();
        nbt.putBoolean("${main.fullName} Tools", true);
        nbt.putString("The most handsome person in the world is", "Winfxk");
        nbt.putString("The ugliest person in the world is", "FFF");
        item.namedTag = nbt;
        item.damage = 0;
    }
    /**
     * 快捷工具过滤选项
     */
    class Filter {
        @Volatile private var whitelist: Boolean = false;
        @Volatile private var blacklist: Boolean = false;
        @Volatile private var whitePlayers = ArrayList<String>();
        @Volatile private var blackPlayers = ArrayList<String>();
        fun init(map: Map<*, *>) {
            whitelist = map["白名单"].objToBoolean(false);
            blacklist = map["黑名单"].objToBoolean(false);
            var any = map["白名单玩家列表"];
            whitePlayers.clear();
            var name: String?;
            if (any != null && any is List<*>)
                for (a in any) {
                    if (a == null) continue;
                    name = any.objToString();
                    if (name.isNullOrBlank()) continue
                    whitePlayers.add(name)
                }
            any = map["黑名单玩家列表"];
            blackPlayers.clear();
            if (any != null && any is List<*>)
                for (a in any) {
                    if (a == null) continue;
                    name = any.objToString();
                    if (name.isNullOrBlank()) continue
                    blackPlayers.add(name)
                }
        }
        /**
         * 判断是否开启了白名单过滤
         */
        fun whitelist() = whitelist;
        /**
         * 判断是否开启了黑名单过滤
         */
        fun blacklist() = blacklist;
        /**
         * 白名单清单明细
         */
        fun whitePlayer() = whitePlayers;
        /**
         * 黑名单清单明细
         */
        fun blackPlayer() = blackPlayers;
        /**
         * 是否可以使用
         */
        fun match(name: String, ignoreCase: Boolean): Boolean {
            if (! whitelist() && ! blacklist()) return true;
            if (blacklist()) {
                if (blackPlayer().contains(name)) return false
                if (ignoreCase) {
                    for (player in blackPlayer())
                        if (player.equals(name, ignoreCase = true))
                            return false
                }
                return true;
            }
            if (whitelist()) {
                if (whitePlayer().contains(name)) return true
                if (ignoreCase) {
                    for (player in whitePlayer())
                        if (player.equals(name, ignoreCase = true))
                            return true
                }
                return false;
            }
            return true;
        }
    }
}