/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.mapstruct.Mapper
 *  org.mapstruct.NullValueCheckStrategy
 */
package cn.springcloud.gray.server.dao.mapper;

import cn.springcloud.gray.server.dao.mapper.ModelMapper;
import cn.springcloud.gray.server.dao.model.OperateRecordDO;
import cn.springcloud.gray.server.module.audit.domain.OperateRecord;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel="spring", nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS)
public interface OperateRecordMapper
extends ModelMapper<OperateRecord, OperateRecordDO> {
}

