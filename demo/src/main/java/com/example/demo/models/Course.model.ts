import { Teacher } from "./Teacher.model";

export interface Course {
    id: number;
    courseName: string;   // Note the naming
    description?: string; // `?` indicates this field is optional
    startDate: Date;
    endDate?: Date;       // `?` indicates this field is optional
    monthlyFee: number;   // Since BigDecimal usually translates to a number in JavaScript/TypeScript
    //teacher: Teacher;     // PLACEHOLDER Assuming you have a Teacher interface in TypeScript
    Groups?: any[];  // PLACEHOLDER Assuming you have a Group interface, and it's optional
}
