package com.ecard.controller;

import com.ecard.pojo.Response;
import com.ecard.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "科室Controller", tags = { "科室操作接口" })
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 新增科室
     *
     * @return
     */
    @PostMapping("/insert")
    @ApiOperation(value = "新增科室", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "subjectCode", value = "监管平台科室编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "subjectName", value = "监管平台科室名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "deptName", value = "本平台科室名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "deputyDoctorCertID", value = "正高级医师身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "deputyDoctorName", value = "正高级医师姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "principalDoctorCertID", value = "副高级医师身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "principalDoctorName", value = "副高级医师姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
    public Response insert(
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "subjectName", required = false) String subjectName,
            @RequestParam(value = "deptName", required = false) String deptName,
            @RequestParam(value = "deputyDoctorCertID", required = false) String deputyDoctorCertID,
            @RequestParam(value = "deputyDoctorName", required = false) String deputyDoctorName,
            @RequestParam(value = "principalDoctorCertID", required = false) String principalDoctorCertID,
            @RequestParam(value = "principalDoctorName", required = false) String principalDoctorName,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag) {
        Response response = departmentService.insert(subjectCode,subjectName,deptName,deputyDoctorCertID,
                deputyDoctorName,principalDoctorCertID,principalDoctorName,disableFlag);
        return response;
    }

    /**
     * 修改科室
     *
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改科室", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "deptID", value = "监管平台科室编号", dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "subjectCode", value = "监管平台科室编号", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "subjectName", value = "监管平台科室名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "deptName", value = "本平台科室名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "deputyDoctorCertID", value = "正高级医师身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "deputyDoctorName", value = "正高级医师姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "principalDoctorCertID", value = "副高级医师身份证号码", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "principalDoctorName", value = "副高级医师姓名", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
    public Response update(
            @RequestParam(value = "deptID", required = false) Long deptID,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "subjectName", required = false) String subjectName,
            @RequestParam(value = "deptName", required = false) String deptName,
            @RequestParam(value = "deputyDoctorCertID", required = false) String deputyDoctorCertID,
            @RequestParam(value = "deputyDoctorName", required = false) String deputyDoctorName,
            @RequestParam(value = "principalDoctorCertID", required = false) String principalDoctorCertID,
            @RequestParam(value = "principalDoctorName", required = false) String principalDoctorName,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag) {
        Response response = departmentService.update(deptID,subjectCode,subjectName,deptName,deputyDoctorCertID,
                deputyDoctorName,principalDoctorCertID,principalDoctorName,disableFlag);
        return response;
    }


    /**
     * 查询科室
     *
     * @return
     */
    @PostMapping("/select")
    @ApiOperation(value = "查询科室", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "deptName", value = "本平台科室名称", dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
    public Response select(
            @RequestParam(value = "deptName", required = false) String deptName,
            @RequestParam(value = "disableFlag", required = false) Integer disableFlag,
            @RequestParam(value="page", required=false, defaultValue="1") Integer page,
            @RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
        Response response = departmentService.select(deptName,disableFlag,page,rows);
        return response;
    }

    /**
     * 查询监管平台科室编码
     *
     * @return
     */
    @PostMapping("/selectJgptDepartment")
    @ApiOperation(value = "查询监管平台科室编码", httpMethod = "POST")
    public Response selectJgptDepartment() {
        Response response = departmentService.selectJgptDepartment();
        return response;
    }

}
