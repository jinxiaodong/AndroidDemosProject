// IMyAidlInterface.aidl
package com.jarvis.aidlserver;

import com.jarvis.aidlserver.ICallbackInterface;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
       void client2server(in ParcelFileDescriptor pfd);

       void registerCallback(ICallbackInterface callback);

       void unregisterCallback(ICallbackInterface callback);
}