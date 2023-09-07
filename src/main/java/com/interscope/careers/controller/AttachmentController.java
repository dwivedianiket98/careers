package com.interscope.careers.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interscope.careers.entity.Attachment;

@RestController
public class AttachmentController {

	@PostMapping(path = "candidate/{id}/upload")
	public ResponseEntity<Attachment> uploadAttachment() {
		return null;
	}
}
