/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.mapstruct.Mapper
 *  org.mapstruct.NullValueCheckStrategy
 */
package cn.springcloud.gray.server.dao.mapper;

import cn.springcloud.gray.server.dao.mapper.ModelMapper;
import cn.springcloud.gray.server.dao.model.GrayServiceDO;
import cn.springcloud.gray.server.module.gray.domain.GrayService;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel="spring", nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS)
public interface GrayServiceMapper
extends ModelMapper<GrayService, GrayServiceDO> {
}

