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
* Created Date: 2025/1/10  13:08 */
package cn.winfxk.minecraft.kis2.config

import cn.winfxk.libk.tool.utils.objToBoolean
import cn.winfxk.libk.tool.utils.objToInt
import cn.winfxk.libk.tool.utils.objToString
import cn.winfxk.minecraft.kis2.Knickers
import cn.winfxk.minecraft.kis2.menu.Systemd
import java.io.File

class Menu {
    @Volatile private var mainMenu: String = "main.json";
    @Volatile private var menuPath: String = Menu.menuFile;
    @Volatile private var menuFile: File = File(Knickers.getMain().dataFolder, "Menus");
    @Volatile private var collectButton: Boolean = true;
    @Volatile private var delay: Int = 3000;

    companion object {
        private const val menuFile = "%menuFile%"
    }

    fun init(map: Map<*, *>) {
        mainMenu = map["主菜单"].objToString() ?: "main.json";
        menuPath = (map["数据存储路径"].objToString() ?: Menu.menuFile).replace(Menu.menuFile, Knickers.getMain().getMenusDir().absolutePath);
        menuFile = File(menuPath)
        collectButton = map["收纳管理按钮"].objToBoolean() ?: true;
        delay = map["传送按钮延迟"].objToInt(3) * 1000;
    }
    /**
     * 返回主菜单文件对象
     */
    fun getMainMenu() = mainMenu;
    /**
     * 返回菜单文件存储路径
     */
    fun getMenuPath() = menuPath;
    /**
     * 返回菜单文件存储File
     */
    fun getMenuFile() = menuFile;
    /**
     * 是否收纳管理按钮
     */
    fun isCollectButton() = collectButton;
    /**
     * 获取传送按钮延迟<毫秒>
     */
    fun getDelay() = delay;
    /**
     * 检查菜单文件是否有问题
     * @return 有多少个可用菜单
     */
    fun inspect(): Array<Int> {
        var allSize = 0;
        var okSize = 0;
        var sbSize = 0;
        val files = getMenuFile().listFiles { a -> a.isFile };
        if (files != null) for (file in files) try {
            Systemd(file)
            okSize ++;
        } catch (e: Exception) {
            sbSize ++;
            Knickers.getMain().logger.error(Knickers.getMain().getMessage().getMessage(
                "菜单",
                "菜单加载异常"
            ).p(
                "name" to file.name,
                "file" to file.absolutePath,
                "error" to e.message,
                "index" to (allSize + 1)
            ))
        } finally {
            allSize ++;
        }
        return arrayOf(
            allSize,
            okSize,
            sbSize
        )
    }
}