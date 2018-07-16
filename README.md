# VMF-Tutorials

This collection of tutorials gives an introduction to VMF and its use in regular Java projects.

## Contents

- [Introduction](https://github.com/miho/VMF-Tutorials/blob/master/README.md#introduction)
- [Defining your first model](https://github.com/miho/VMF-Tutorials/blob/master/VMF-Tutorial-01/README.md)

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

