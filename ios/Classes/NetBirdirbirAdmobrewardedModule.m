/**
 * admobrewarded
 *
 * Created by Didier Fontaine
 * Copyright (c) 2020 Your Company. All rights reserved.
 */

#import "NetBirdirbirAdmobrewardedModule.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"

@implementation NetBirdirbirAdmobrewardedModule

#pragma mark Internal

// This is generated for your module, please do not change it
- (id)moduleGUID
{
  return @"08bf1d38-2807-41e7-8d44-873f2a4fe174";
}

// This is generated for your module, please do not change it
- (NSString *)moduleId
{
  return @"net.birdirbir.admobrewarded";
}

#pragma mark Lifecycle

- (void)startup
{
  // This method is called when the module is first loaded
  // You *must* call the superclass
  [super startup];
  DebugLog(@"[DEBUG] %@ loaded", self);
}

#pragma Public APIs

- (NSString *)example:(id)args
{
  // Example method. 
  // Call with "MyModule.example(args)"
  return @"hello world";
}

- (NSString *)exampleProp
{
  // Example property getter. 
  // Call with "MyModule.exampleProp" or "MyModule.getExampleProp()"
  return @"Titanium rocks!";
}

- (void)setExampleProp:(id)value
{
  // Example property setter. 
  // Call with "MyModule.exampleProp = 'newValue'" or "MyModule.setExampleProp('newValue')"
}

@end
