/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elson.basecore.base;

import android.support.annotation.NonNull;

public interface BaseView<T> {

    /**
     * 初始化presenter
     * <p>
     * 此方法返回的presenter对象不可为空
     */
    @NonNull
    BasePresenter initPresenter();

    /**
     * 判断activity是否结束了
     * @return
     */
    boolean isActivityFinish();

    /**
     * 显示等待dialog
     */
    void showLoading(String msg);

    /**
     * 隐藏等待dialog
     */
    void hideLoading();

    /**
     * 显示消息
     * @param msg
     */
    void showToast(String msg);

}
