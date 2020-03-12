package example.spring.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class XmlMessage {

    @XmlElement
    private Long id;

    @XmlElement
    private String info;

    @XmlElement
    private String href;

    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @XmlTransient
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
