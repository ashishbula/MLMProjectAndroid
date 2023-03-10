/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.network;

import java.io.IOException;

public class NoNetworkException extends IOException {

    public NoNetworkException() {
        super("No Internet Connection");
    }
}
