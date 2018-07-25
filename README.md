# VMF-Tutorials [![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=VMF:%20The%20new%20modeling%20framework%20for%20Java!&url=https://github.com/miho/VMF&via=mihosoft&hashtags=vmf,java,mdd,developers)
[![Join the chat at https://gitter.im/VMF_/Lobby](https://badges.gitter.im/VMF_/Lobby.svg)](https://gitter.im/VMF_/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This collection of tutorials gives an introduction to VMF and its use in regular Java projects.

## Contents

- [Introduction](https://github.com/miho/VMF-Tutorials/blob/master/README.md#introduction)
- [Defining your first model](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md)
- [Using the Change Notification API](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-02/README.md)
- [Containment References](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-03/README.md)
- [Undo/Redo API](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-04/README.md)
- [Using the Builder API](https://github.com/miho/VMF-Tutorials/tree/master/VMF-Tutorial-05/README.md)

## Introduction

VMF is a lightweight modeling framework for the Java platform. It generates/supports:

- getters and setters
- default values
- containment
- builder API
- equals() and hashCode()
- deep and shallow cloning
- change notification
- undo/redo
- object graph traversal API via iterators and streams
- immutable types and read-only wrappers
- delegation
- ...

A VMF model consists of annotated Java interfaces. We could call this "wannabe" code. We just specify the interface and its properties and get a rich implementation that implements the property setters and getters, builders and much more. Even for a simple model VMF generated a lot of useful API:

<img src="resources/img/vmf-01.svg">

VMF integrates well into manual Java implementations. It comes with a Gradle plugin which means that using VMF is very simple. The tutorials will walk you through all major aspects of VMF.

