/*******************************************************************************
 *
 *    Copyright 2020 Adobe. All rights reserved.
 *    This file is licensed to you under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License. You may obtain a copy
 *    of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software distributed under
 *    the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
 *    OF ANY KIND, either express or implied. See the License for the specific language
 *    governing permissions and limitations under the License.
 *
 ******************************************************************************/
package com.adobe.cq.commerce.core.components.internal.models.v1.categorylist;

import java.util.Set;

import javax.annotation.Nonnull;

import org.apache.sling.api.resource.Resource;

import com.adobe.cq.commerce.core.components.internal.models.v1.AbstractAssetsProvider;
import com.adobe.cq.commerce.core.components.models.categorylist.FeaturedCategoryList;
import com.adobe.cq.commerce.magento.graphql.CategoryTree;

public class CategoryListAssetsProvider extends AbstractAssetsProvider {

    public boolean canHandle(@Nonnull Resource resource) {
        return resource.isResourceType(FeaturedCategoryListImpl.RESOURCE_TYPE);
    }

    public void addAssetPaths(@Nonnull Resource resource, @Nonnull Set<String> assetPaths) {
        FeaturedCategoryList featuredCategoryList = canHandle(resource) ? resource.adaptTo(FeaturedCategoryList.class) : null;
        if (featuredCategoryList != null) {
            for (CategoryTree item : featuredCategoryList.getCategories()) {
                String imageUri = item.getImage();
                if (isAemAsset(imageUri)) {
                    assetPaths.add(imageUri);
                }
            }
        }
    }
}