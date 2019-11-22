package com.ecard.controller;

import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.RoleQr;
import com.ecard.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "角色Controller", tags = { "角色操作接口" })
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询角色列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询所有角色", httpMethod = "GET")
    public Response roleList(
            @ApiParam(name = "roleName", value = "角色名字", required = false) @RequestParam(value = "roleName", required = false) String roleName,
            @ApiParam(name = "page", value = "页", required = false) @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @ApiParam(name = "rows", value = "行", required = false) @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows) {
        Response response = roleService.list(roleName, page, rows);
        return response;
    }

    /**
     * 查询角色详情
     *
     * @param roleNo
     * @return
     */
    @GetMapping("/selectByNo")
    @ApiOperation(value = "查询角色详情", httpMethod = "GET")
    public Response selectByNo(
            @ApiParam(name = "roleNo", value = "角色编号", required = true) @RequestParam(value = "roleNo", required = true) Long roleNo) {
        Response response = roleService.selectByNo(roleNo);
        return response;
    }

    /**
     * 新增角色
     *
     * @param roleQr
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增角色", httpMethod = "POST")
    public Response save(@RequestBody RoleQr roleQr) {
        Response response = roleService.save(roleQr);
        return response;
    }

    /**
     * 修改角色
     *
     * @param roleQr
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改角色", httpMethod = "POST")
    public Response update(@RequestBody RoleQr roleQr) {
        Response response = roleService.update(roleQr);
        return response;
    }

    /**
     * 删除角色
     *
     * @param roleNo
     * @return
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除角色", httpMethod = "POST")
    public Response delete(
            @ApiParam(name = "roleNo", value = "角色编号", required = true) @RequestParam(value = "roleNo", required = true) Long roleNo) {
        Response response = roleService.delete(roleNo);
        return response;
    }

    /**
     * 全部权限查询
     *
     * @return
     */
    @PostMapping("/authorityList")
    @ApiOperation(value = "查询所有权限", httpMethod = "POST")
    public Response authorityList() {
        Response response = roleService.authorityList();
        return response;
    }
}
