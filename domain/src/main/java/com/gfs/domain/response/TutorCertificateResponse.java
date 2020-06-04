package com.gfs.domain.response;

import com.gfs.domain.document.ObjectIdDocument;
import com.gfs.domain.document.TutorCertificate;
import lombok.Data;

@Data
public class TutorCertificateResponse extends DocumentResponse {
    private String certificate_id;
    private String name;
    private String main_photo;
    private String additional_photo;

    public TutorCertificateResponse() {
    }

    public TutorCertificateResponse(String hex_id, long created_at, long updated_at) {
        super(hex_id, created_at, updated_at);
    }

    public TutorCertificateResponse(ObjectIdDocument document) {
        super(document);
    }

    public TutorCertificateResponse(String certificate_id, String name, String main_photo, String additional_photo) {
        this.certificate_id = certificate_id;
        this.name = name;
        this.main_photo = main_photo;
        this.additional_photo = additional_photo;
    }

    public TutorCertificateResponse(String hex_id, long created_at, long updated_at, String certificate_id, String name, String main_photo, String additional_photo) {
        super(hex_id, created_at, updated_at);
        this.certificate_id = certificate_id;
        this.name = name;
        this.main_photo = main_photo;
        this.additional_photo = additional_photo;
    }

    public TutorCertificateResponse(ObjectIdDocument document, String certificate_id, String name, String main_photo, String additional_photo) {
        super(document);
        this.certificate_id = certificate_id;
        this.name = name;
        this.main_photo = main_photo;
        this.additional_photo = additional_photo;
    }

    public TutorCertificateResponse(TutorCertificate certificate) {
        this(certificate,
                certificate.getCertificate_id(),
                certificate.getName(),
                certificate.getMain_photo(),
                certificate.getAdditional_photo());
    }
}
