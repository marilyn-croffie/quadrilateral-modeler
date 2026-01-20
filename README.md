# Quadrilateral Modeler

A Java library for creating and analyzing quadrilateral shapes with mathematical precision and geometric validation.

## Overview

Implements the complete quadrilateral hierarchy: Quadrilateral → Trapezoid → Parallelogram → Rectangle → Square. Each shape enforces its geometric constraints through progressive validation, preventing invalid shapes at construction time.

## Features

- **Immutable Point class** with epsilon-based equality for floating-point precision
- **Hierarchical validation** using the Template Method pattern
- **Automatic vertex ordering** via polar coordinate sorting from centroid
- **Shape-specific area calculations** optimized for each quadrilateral type
- **Geometric utilities** for vector operations, distance, parallelism, and perpendicularity
- **Comprehensive Javadoc** with usage examples and implementation notes

## Class Hierarchy

```
Quadrilateral (abstract)
    └── Trapezoid (1 pair parallel sides)
        └── Parallelogram (2 pairs parallel sides)
            └── Rectangle (+ right angles)
                └── Square (+ equal sides)
```

## Implementation Details

- Epsilon tolerance (1e-9) for reliable floating-point comparisons
- Cross product and dot product for geometric relationships
- Perpendicular distance calculation for height measurements
- Thread-safe immutable design
- Validation prevents collinear points, duplicates, and invalid configurations

## Author

Marilyn Croffie 
