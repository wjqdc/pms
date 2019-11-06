package com.qdc.pre.service;

import com.qdc.pre.bean.Attachment;
import com.qdc.pre.mapper.AttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;
    public void saveInfo(Attachment atta) {
        attachmentMapper.insert(atta);
    }

}
