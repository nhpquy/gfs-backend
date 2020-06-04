package com.gfs.api.controller;

import com.gfs.api.annotation.ApiRestController;
import com.gfs.domain.constant.SwaggerTag;
import com.gfs.domain.utils.DateTimeProvider;
import com.gfs.domain.utils.HttpProvider;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Api(tags = SwaggerTag.up)
@ApiRestController(value = "")
public class RestUpController {

    @ApiOperation(value = "Checking Up")
    @ApiResponse(code = 200, message = "System started successfully", examples = @Example(value = @ExampleProperty(value = "up:127.0.0.1:May-02-2020 10:07:46 UTC")))
    @GetMapping(value = "/up")
    public String up(HttpServletRequest servletRequest) {
        return "up:" + HttpProvider.getIpAddress(servletRequest) + ":" + DateTimeProvider.convertTimestampToStringDate(System.currentTimeMillis());
    }
}
