// src/environment.ts
export const environment = {
    // Configuración para desarrollo local
    local: {
        employeeApi: 'http://localhost:9090',
        payrollApi: 'http://localhost:9091',
        performanceApi: 'http://localhost:9092',
        scheduleApi: 'http://localhost:9093'
    },
    // Configuración para producción (Docker)
    docker: {
        employeeApi: 'http://employee-service:9090',
        payrollApi: 'http://payroll-service:9091',
        performanceApi: 'http://performance-service:9092',
        scheduleApi: 'http://schedule-service:9093'
    }
} as const;

// Selección automática basada en el entorno
export const apiConfig = import.meta.env.MODE === 'production'
    ? environment.docker
    : environment.local;