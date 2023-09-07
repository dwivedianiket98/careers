package com.interscope.careers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interscope.careers.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
