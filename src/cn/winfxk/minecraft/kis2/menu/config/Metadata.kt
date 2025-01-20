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
* Created Date: 2025/1/18  08:06 */
package cn.winfxk.minecraft.kis2.menu.config

import cn.winfxk.libk.tool.utils.objToInt
import cn.winfxk.libk.tool.utils.objToString
import cn.winfxk.minecraft.kis2.menu.Systemd

class Metadata{
    private var createdBy: String? = null;
    private var creationDate: String? = null;
    private var createVersion: String? = null;
    private var updatedBy: String? = null;
    private var updateDate: String? = null;
    private var updateVersion: String? = null;
    private var updateCount: Int = 0;
    private var accessCount: Int = 0;
    private var lastAccessDate: String? = null;
    private var lastAccessedBy: String? = null;
    /**
     * 创建人
     */
    fun getCreatedBy() = createdBy;
    /**
     * 创建日期
     */
    fun getCreationDate() = creationDate;
    /**
     * 创建时的版本
     */
    fun getCreateVersion() = createVersion;
    /**
     * 最近修改人
     */
    fun getUpdatedBy() = updatedBy;
    /**
     * 最近修改日期
     */
    fun getUpdateDate() = updateDate;
    /**
     * 最近修改时的版本
     */
    fun getUpdateVersion() = updateVersion;
    /**
     * 修改次数
     */
    fun getUpdateCount() = updateCount;
    /**
     * 被访问次数
     */
    fun getAccessCount() = accessCount;
    /**
     * 最近被访问日期
     */
    fun getLastAccessDate() = lastAccessDate;
    /**
     * 最近访问人
     */
    fun getLastAccessedBy() = lastAccessedBy;
    fun reload(any: Any?) {
        createdBy = null;
        creationDate = null;
        createVersion = null;
        updatedBy = null;
        updateDate = null;
        updateVersion = null;
        lastAccessDate = null;
        lastAccessedBy = null;
        updateCount = 0;
        accessCount = 0;
        if (any == null || any !is Map<*, *>) return;
        createdBy = any["created by"].objToString();
        creationDate = any["creation date"].objToString();
        createVersion = any["create version"].objToString();
        updatedBy = any["updated by"].objToString();
        updateDate = any["update date"].objToString();
        updateVersion = any["update version"].objToString();
        updateCount = any["update count"].objToInt(0);
        accessCount = any["access count"].objToInt(0);
        lastAccessDate = any["last access date"].objToString();
        lastAccessedBy = any["last accessed by"].objToString();
    }

}