package com.hj.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hj.o2o.dto.ShopExecution;
import com.hj.o2o.entity.Area;
import com.hj.o2o.entity.PersonInfo;
import com.hj.o2o.entity.Shop;
import com.hj.o2o.entity.ShopCategory;
import com.hj.o2o.service.AreaService;
import com.hj.o2o.service.ShopService;
import com.hj.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HUJING
 * @Date: 2019/3/6 20:38
 * @Version 1.0
 * @Description:
 */

@Controller
@RequestMapping("/shopadmin")
public class shopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private AreaService areaService;

}
