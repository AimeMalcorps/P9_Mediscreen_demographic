package com.mediscreen.demographic.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "history-notes", url = "host.docker.internal:8082")
public interface HistoryNotesProxy {
	
	@GetMapping(value = "/patHistory/delete/all/{id}")
    public boolean deleteNotesByPatientId(@PathVariable Integer id);

}
