// Home Asset Manager
// Copyright (C) 2019  Denny Chambers

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package net.bugfixer.hamsvr.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.bugfixer.hamsvr.exception.ResourceNotFoundException;
import net.bugfixer.hamsvr.model.Asset;
import net.bugfixer.hamsvr.repository.AssetRepository;

@RestController
public class AssetController {
	
	@Autowired
    private AssetRepository assetRepository;

	@GetMapping("/assets")
    public Page<Asset> getAssets(Pageable pageable) {
        return assetRepository.findAll(pageable);
    }
	
	@PostMapping("/assets")
	public Asset createAsset(@Valid @RequestBody Asset asset) {
		return assetRepository.save(asset);
	}
	
	@GetMapping("/asset/{assetId}")
    public Optional<Asset> getAsset(@PathVariable UUID assetId) {
        return assetRepository.findById(assetId);
    }
	
	@PutMapping("/asset/{assetId}")
	public Asset updateAsset(@PathVariable UUID assetId, @Valid @RequestBody Asset assetRequest) {
		return assetRepository.findById(assetId)
                .map(asset -> {
                    asset.setName(assetRequest.getName());
                    asset.setManufacturer(assetRequest.getManufacturer());
                    asset.setModel(assetRequest.getModel());
                    asset.setSerialId(assetRequest.getSerialId());
                    asset.setPurchasePrice(assetRequest.getPurchasePrice());
                    asset.setLocation(assetRequest.getLocation());
                    asset.setDescription(assetRequest.getDescription());
                    asset.setTags(assetRequest.getTags());
                    return assetRepository.save(asset);
                }).orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + assetId.toString()));
	}
	
	@DeleteMapping("/asset/{assetId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable UUID assetId) {
        return assetRepository.findById(assetId)
                .map(asset -> {
                    assetRepository.delete(asset);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + assetId.toString()));
    }
}
