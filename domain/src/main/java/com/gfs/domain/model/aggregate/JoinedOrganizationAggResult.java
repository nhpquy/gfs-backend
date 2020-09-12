package com.gfs.domain.model.aggregate;

import com.gfs.domain.document.JoinedOrganization;
import lombok.Data;
import org.springframework.data.annotation.Reference;

@Data
public class JoinedOrganizationAggResult extends JoinedOrganization {
    @Reference // ignored index in doc
    private OrganizationAggResult organization;
    @Reference
    private TutorAggResult owner;
    @Reference
    private TutorAggResult partner;
}
