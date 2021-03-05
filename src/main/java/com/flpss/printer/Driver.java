package com.flpss.printer;


import org.usb4java.Context;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;
import org.usb4java.DeviceList;
import org.usb4java.DeviceDescriptor;
import org.usb4java.Device;

import java.util.ArrayList;
import java.util.List;


public class Driver {



    private Context context = new Context();



    public Driver() {

        int result = LibUsb.init(this.context);

        if (result != LibUsb.SUCCESS) {
            throw new LibUsbException("Unable to initialize libusb.", result);
        }
    }



    public List<DeviceDescriptor> deviceList() {
        List<DeviceDescriptor> descriptors = new ArrayList<>();

        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(this.context, list);

        if (result < 0) {
            throw new LibUsbException("Unable to get device list", result);
        }

        try {
            for (Device device : list) {

                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);

                if (result < 0) {
                    throw new LibUsbException("Unable to read device descriptor", result);
                }

                descriptors.add(descriptor);
            }
        }
        finally {
            LibUsb.freeDeviceList(list, true);
        }

        LibUsb.exit(this.context);

        return descriptors;
    }



    public static String getStatus() {

        String body = "";
        Driver driver = new Driver();
        List<DeviceDescriptor> devices = driver.deviceList();

        for (DeviceDescriptor device : devices) {

            body = body.concat("" + device.dump().replaceAll("(?m)^", "  ") + "\n");
        }

        return body;
    }
}
