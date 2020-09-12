package com.gfs.domain.request;

import com.gfs.domain.enums.OrgInvitationStatus;
import lombok.Data;

@Data
public class GetOrgInvitationPagingRequest extends PagingRequest {
    private OrgInvitationStatus invite_status;
}
