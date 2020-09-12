package com.gfs.domain.request;

import com.gfs.domain.enums.OrgInvitationStatus;
import lombok.Data;

@Data
public class GetPartnerInvitationPagingRequest extends PagingRequest {
    private OrgInvitationStatus invite_status;
}
