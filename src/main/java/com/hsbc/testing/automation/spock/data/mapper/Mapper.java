package com.hsbc.testing.automation.spock.data.mapper;

import com.hsbc.testing.automation.spock.builder.Builder;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by sudhe on 31-07-2016.
 */
public interface Mapper extends RowMapper {
    Builder getBuilder();
}
