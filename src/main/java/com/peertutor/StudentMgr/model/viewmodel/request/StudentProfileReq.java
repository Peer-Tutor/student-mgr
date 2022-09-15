package com.peertutor.StudentMgr.model.viewmodel.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class StudentProfileReq {
    @NotNull
    @NotEmpty
    public String name;

    @NotNull
    @NotEmpty
    public String sessionToken;

    public String displayName;

    @NotNull
    @NotEmpty
    public String accountName;

    public String introduction;

    public String subjects;
}
